import { useEffect, useState } from 'react';
import { Delivery } from '../types/Delivery';
import * as deliveryAPI from '../api/deliveryAPI';

export const useDeliveries = () => {
    const [deliveries, setDeliveries] = useState<Delivery[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchDeliveries = async () => {
            try {
                const data = await deliveryAPI.getDeliveries();
                setDeliveries(data);
            } catch (err: any) {
                setError(err.message || 'Error fetching deliveries');
            } finally {
                setLoading(false);
            }
        };

        fetchDeliveries();
    }, []);

    return { deliveries, loading, error };
};
