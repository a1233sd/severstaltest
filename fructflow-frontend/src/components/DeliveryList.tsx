import React from 'react';
import { Delivery } from '../types/Delivery';

interface DeliveryListProps {
    deliveries: Delivery[];
    onSelectDelivery?: (delivery: Delivery) => void;
    onDeleteDelivery?: (id: number) => void;
}

const DeliveryList: React.FC<DeliveryListProps> = ({ deliveries, onSelectDelivery, onDeleteDelivery }) => {
    return (
        <div>
            <h2>Список поставок</h2>
            {deliveries.length === 0 ? (
                <p>Поставки не найдены.</p>
            ) : (
                <table
                    border={1}
                    cellPadding={5}
                    style={{ width: '100%', borderCollapse: 'collapse', marginTop: '1rem' }}
                >
                    <thead style={{ backgroundColor: '#4a148c', color: '#fff' }}>
                    <tr>
                        <th>ID</th>
                        <th>Поставщик</th>
                        <th>Дата поставки</th>
                        <th>Действия</th>
                    </tr>
                    </thead>
                    <tbody>
                    {deliveries.map((delivery) => (
                        <tr key={delivery.id}>
                            <td>{delivery.id}</td>
                            <td>{delivery.supplier?.name}</td>
                            <td>{delivery.deliveryDate}</td>
                            <td>
                                {onDeleteDelivery && delivery.id !== undefined && (
                                    <button onClick={() => onDeleteDelivery(delivery.id)}>Удалить</button>
                                )}
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            )}
        </div>
    );
};

export default DeliveryList;
