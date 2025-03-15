import React from 'react';
import { Link } from 'react-router-dom';

const Header: React.FC = () => {
    return (
        <header style={{ padding: '1rem', backgroundColor: '#4a148c', color: '#fff' }}>
            <h1>Fructflow</h1>
            <nav>
                <Link to="/" style={{ marginRight: '1rem', color: '#fff', textDecoration: 'none' }}>
                    Панель управления
                </Link>
                <Link to="/deliveries" style={{ marginRight: '1rem', color: '#fff', textDecoration: 'none' }}>
                    Поставки
                </Link>
                <Link to="/reports" style={{ color: '#fff', textDecoration: 'none' }}>
                    Отчеты
                </Link>
            </nav>
        </header>
    );
};

export default Header;
