export type Category = 'APPLE' | 'PEAR';

export interface Product {
    id?: number;         // Опционально, если ID генерируется на сервере
    name: string;        // Название продукта
    category: Category;  // Категория продукта: 'APPLE' или 'PEAR'
    description: string; // Описание продукта
}
