export interface Supplier {
    id?: number;         // Опционально, если ID генерируется на сервере
    name: string;        // Название поставщика
    email: string;       // Email поставщика
    phone: string;       // Телефон поставщика
    address: string;     // Адрес поставщика
}
