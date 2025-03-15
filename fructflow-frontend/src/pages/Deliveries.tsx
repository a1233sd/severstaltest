import React, { useEffect, useState } from 'react';
import Header from '../components/Header';
import Footer from '../components/Footer';
import DeliveryForm from '../components/DeliveryForm';
import DeliveryList from '../components/DeliveryList';
import { Delivery } from '../types/Delivery';
import { Supplier } from '../types/Supplier';
import { Product } from '../types/Product';
import * as deliveryAPI from '../api/deliveryAPI';
import * as supplierAPI from '../api/supplierAPI';
import * as productAPI from '../api/productAPI';

const Deliveries: React.FC = () => {
    const [deliveries, setDeliveries] = useState<Delivery[]>([]);
    const [editingDelivery, setEditingDelivery] = useState<Delivery | null>(null);
    const [suppliers, setSuppliers] = useState<Supplier[]>([]);
    const [products, setProducts] = useState<Product[]>([]);

    const fetchDeliveries = async () => {
        try {
            const data = await deliveryAPI.getDeliveries();
            setDeliveries(data);
        } catch (error) {
            console.error('Ошибка при получении поставок', error);
        }
    };

    const fetchSuppliersAndProducts = async () => {
        try {
            const suppliersData = await supplierAPI.getSuppliers();
            const productsData = await productAPI.getProducts();
            setSuppliers(suppliersData);
            setProducts(productsData);
        } catch (error) {
            console.error('Ошибка при получении поставщиков или продуктов', error);
        }
    };

    useEffect(() => {
        fetchDeliveries();
        fetchSuppliersAndProducts();
    }, []);

    const handleCreate = async (delivery: Delivery) => {
        try {
            const created = await deliveryAPI.createDelivery(delivery);
            setDeliveries([...deliveries, created]);
        } catch (error) {
            console.error('Ошибка при создании поставки', error);
        }
    };

    const handleUpdate = async (delivery: Delivery) => {
        if (delivery.id !== undefined) {
            try {
                const updated = await deliveryAPI.updateDelivery(delivery.id, delivery);
                setDeliveries(deliveries.map((d) => (d.id === updated.id ? updated : d)));
                setEditingDelivery(null);
            } catch (error) {
                console.error('Ошибка при обновлении поставки', error);
            }
        }
    };

    const handleDelete = async (id: number) => {
        try {
            await deliveryAPI.deleteDelivery(id);
            setDeliveries(deliveries.filter((d) => d.id !== id));
        } catch (error) {
            console.error('Ошибка при удалении поставки', error);
        }
    };

    return (
        <div>
            <Header />
            <main style={{ padding: '1rem' }}>
                <h2>Поставки</h2>
                <DeliveryForm
                    initialDelivery={editingDelivery || undefined}
                    onSubmit={editingDelivery ? handleUpdate : handleCreate}
                    supplierOptions={suppliers}
                    productOptions={products}
                />
                <DeliveryList
                    deliveries={deliveries}
                    onSelectDelivery={(delivery) => setEditingDelivery(delivery)}
                    onDeleteDelivery={handleDelete}
                />
            </main>
            <Footer />
        </div>
    );
};

export default Deliveries;
