import React from 'react';
import { Link } from 'react-router-dom';

const NotFound: React.FC = () => {
    return (
        <div style={{ textAlign: 'center', padding: '2rem' }}>
            <h2>404 - Страница не найдена</h2>
            <p>Запрашиваемая страница не существует.</p>
            <Link to="/">Вернуться на главную</Link>
        </div>
    );
};

export default NotFound;
