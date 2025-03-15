import { Supplier } from './Supplier';
import { Product } from './Product';

export interface DeliveryItem {
    id?: number;
    product: Product; // теперь передаем полноценный объект продукта
    quantity: number;
    cost: number;
}

export interface Delivery {
    id?: number;
    supplier: Supplier; // теперь передаем объект поставщика
    deliveryDate: string;
    deliveryItems: DeliveryItem[];
}
