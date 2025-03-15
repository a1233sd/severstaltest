import React from 'react';
import Header from '../components/Header';
import Footer from '../components/Footer';
import { Link } from 'react-router-dom';

const Dashboard: React.FC = () => {
    return (
        <div>
            <Header />
            <main style={{ padding: '1rem' }}>
                <h2>Панель управления</h2>
                <p>Добро пожаловать в систему Fructflow!</p>
                <nav>
                    <ul>
                        <li>
                            <Link to="/deliveries">Управление поставками</Link>
                        </li>
                        <li>
                            <Link to="/reports">Просмотр отчётов</Link>
                        </li>
                    </ul>
                </nav>
            </main>
            <Footer />
        </div>
    );
};

export default Dashboard;
