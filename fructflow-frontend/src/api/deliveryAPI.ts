import { BASE_URL } from './index';
import { Delivery } from '../types/Delivery';

export const getDeliveries = async (): Promise<Delivery[]> => {
    const response = await fetch(`${BASE_URL}/deliveries`);
    if (!response.ok) {
        throw new Error('Error fetching deliveries');
    }
    return response.json();
};

export const getDeliveryById = async (id: number): Promise<Delivery> => {
    const response = await fetch(`${BASE_URL}/deliveries/${id}`);
    if (!response.ok) {
        throw new Error(`Error fetching delivery with id ${id}`);
    }
    return response.json();
};

export const createDelivery = async (delivery: Delivery): Promise<Delivery> => {
    const response = await fetch(`${BASE_URL}/deliveries`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(delivery),
    });
    if (!response.ok) {
        throw new Error('Error creating delivery');
    }
    return response.json();
};

export const updateDelivery = async (id: number, delivery: Delivery): Promise<Delivery> => {
    const response = await fetch(`${BASE_URL}/deliveries/${id}`, {
        method: 'PUT', // Если потребуется частичное обновление, можно изменить на PATCH
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(delivery),
    });
    if (!response.ok) {
        throw new Error(`Error updating delivery with id ${id}`);
    }
    return response.json();
};

export const deleteDelivery = async (id: number): Promise<void> => {
    const response = await fetch(`${BASE_URL}/deliveries/${id}`, {
        method: 'DELETE',
    });
    if (!response.ok) {
        throw new Error(`Error deleting delivery with id ${id}`);
    }
};
