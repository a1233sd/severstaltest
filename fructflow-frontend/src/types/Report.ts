export interface Report {
    supplierId: number;   // Идентификатор поставщика
    supplierName: string; // Название поставщика
    totalWeight: number;  // Суммарное количество/вес поставленного продукта
    totalCost: number;    // Суммарная стоимость поставок
    // Дополнительно можно добавить другие поля в зависимости от требований отчёта
}
