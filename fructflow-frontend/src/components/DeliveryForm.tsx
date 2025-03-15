import React, { useState, ChangeEvent, FormEvent } from 'react';
import { Delivery, DeliveryItem } from '../types/Delivery';
import { Supplier } from '../types/Supplier';
import { Product } from '../types/Product';

interface DeliveryFormProps {
    initialDelivery?: Delivery;
    supplierOptions: Supplier[];
    productOptions: Product[];
    onSubmit: (delivery: Delivery) => void;
}

const DeliveryForm: React.FC<DeliveryFormProps> = ({ initialDelivery, supplierOptions, productOptions, onSubmit }) => {
    const [selectedSupplier, setSelectedSupplier] = useState<Supplier | null>(
        initialDelivery?.supplier || null
    );
    const [deliveryDate, setDeliveryDate] = useState<string>(initialDelivery?.deliveryDate || '');
    const [deliveryItems, setDeliveryItems] = useState<DeliveryItem[]>(
        initialDelivery?.deliveryItems || []
    );

    const handleItemChange = (
        index: number,
        e: ChangeEvent<HTMLSelectElement | HTMLInputElement>
    ) => {
        const { name, value } = e.target;
        setDeliveryItems(prev =>
            prev.map((item, i) => {
                if (i === index) {
                    if (name === 'product') {
                        // Находим выбранный продукт по id
                        const prod = productOptions.find(p => p.id === Number(value));
                        return { ...item, product: prod! };
                    } else {
                        return { ...item, [name]: Number(value) };
                    }
                }
                return item;
            })
        );
    };

    const addItem = () => {
        // По умолчанию выбираем первый продукт из списка (если есть)
        const defaultProduct = productOptions[0];
        setDeliveryItems([...deliveryItems, { product: defaultProduct, quantity: 0, cost: 0 }]);
    };

    const removeItem = (index: number) => {
        setDeliveryItems(deliveryItems.filter((_, i) => i !== index));
    };

    const handleSubmit = (e: FormEvent) => {
        e.preventDefault();
        if (!selectedSupplier) return;
        const delivery: Delivery = {
            supplier: selectedSupplier,
            deliveryDate,
            deliveryItems,
        };
        onSubmit(delivery);
    };

    return (
        <form onSubmit={handleSubmit} style={{ marginBottom: '2rem' }}>
            <div className="form-group">
                <label>Поставщик:</label>
                <select
                    value={selectedSupplier ? selectedSupplier.id : ''}
                    onChange={(e) => {
                        const supplier = supplierOptions.find(s => s.id === Number(e.target.value));
                        setSelectedSupplier(supplier || null);
                    }}
                    required
                >
                    <option value="" disabled>
                        Выберите поставщика
                    </option>
                    {supplierOptions.map((supplier) => (
                        <option key={supplier.id} value={supplier.id}>
                            {supplier.name}
                        </option>
                    ))}
                </select>
            </div>
            <div className="form-group">
                <label>Дата поставки:</label>
                <input
                    type="date"
                    value={deliveryDate}
                    onChange={(e) => setDeliveryDate(e.target.value)}
                    required
                />
            </div>
            <div>
                <h3>Элементы поставки</h3>
                {deliveryItems.map((item, index) => (
                    <div
                        key={index}
                        style={{
                            marginBottom: '1rem',
                            border: '1px solid #ccc',
                            padding: '0.5rem',
                            borderRadius: '4px',
                        }}
                    >
                        <div className="form-group">
                            <label>Продукт:</label>
                            <select
                                name="product"
                                value={item.product.id}
                                onChange={(e) => handleItemChange(index, e)}
                                required
                            >
                                {productOptions.map((prod) => (
                                    <option key={prod.id} value={prod.id}>
                                        {prod.name}
                                    </option>
                                ))}
                            </select>
                        </div>
                        <div className="form-group">
                            <label>Количество:</label>
                            <input
                                type="number"
                                step="0.01"
                                name="quantity"
                                value={item.quantity}
                                onChange={(e) => handleItemChange(index, e)}
                                required
                            />
                        </div>
                        <div className="form-group">
                            <label>Стоимость:</label>
                            <input
                                type="number"
                                step="0.01"
                                name="cost"
                                value={item.cost}
                                onChange={(e) => handleItemChange(index, e)}
                                required
                            />
                        </div>
                        <button type="button" onClick={() => removeItem(index)}>
                            Удалить элемент
                        </button>
                    </div>
                ))}
                <button type="button" onClick={addItem}>
                    Добавить элемент
                </button>
            </div>
            <button type="submit">Отправить поставку</button>
        </form>
    );
};

export default DeliveryForm;
