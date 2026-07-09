import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { Dumbbell, User } from 'lucide-react';
import { useAuth } from '../context/AuthContext';

const Navbar: React.FC = () => {
  const { isAuthenticated, logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/');
  };

  return (
    <header style={{ 
      backgroundColor: 'var(--bg-card)', 
      padding: '16px 0', 
      borderBottom: '1px solid #333'
    }}>
      <div className="container" style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
        <Link to="/" style={{ display: 'flex', alignItems: 'center', gap: '8px', color: 'var(--primary-color)' }}>
          <Dumbbell size={28} />
          <span style={{ fontSize: '1.5rem', fontWeight: 'bold' }}>FitZone</span>
        </Link>
        
        <nav style={{ display: 'flex', gap: '24px', alignItems: 'center' }}>
          <Link to="/classes" style={{ fontWeight: 500 }}>Classes</Link>
          <Link to="/trainers" style={{ fontWeight: 500 }}>Trainers</Link>
          <Link to="/memberships" style={{ fontWeight: 500 }}>Memberships</Link>
          <div style={{ display: 'flex', gap: '12px', alignItems: 'center', marginLeft: '16px' }}>
            {isAuthenticated ? (
              <>
                {user?.role === 'ROLE_ADMIN' && (
                  <Link to="/admin" style={{ fontWeight: 500, color: 'var(--accent-blue)', marginRight: '10px' }}>Admin Panel</Link>
                )}
                <Link to="/dashboard">
                  <User size={24} />
                </Link>
                <button onClick={handleLogout} className="btn-primary" style={{ padding: '8px 16px' }}>Logout</button>
              </>
            ) : (
              <Link to="/login" className="btn-primary" style={{ padding: '8px 16px' }}>Login</Link>
            )}
          </div>
        </nav>
      </div>
    </header>
  );
};

export default Navbar;
