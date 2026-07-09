import React, { useState, useEffect } from 'react';
import api from '../utils/api';

const AdminDashboard: React.FC = () => {
  const [activeTab, setActiveTab] = useState<'classes' | 'trainers' | 'members'>('classes');
  const [classes, setClasses] = useState<any[]>([]);

  useEffect(() => {
    if (activeTab === 'classes') {
      fetchClasses();
    }
  }, [activeTab]);

  const fetchClasses = async () => {
    try {
      const res = await api.get('/classes');
      if (res.data.success) {
        setClasses(res.data.data);
      }
    } catch (err) {
      console.error(err);
    }
  };

  const handleDeleteClass = async (id: number) => {
    try {
      const res = await api.delete(`/classes/${id}`);
      if (res.data.success) {
        setClasses(classes.filter(c => c.id !== id));
      }
    } catch (err) {
      alert('Failed to delete class');
    }
  };

  return (
    <div className="container" style={{ paddingTop: '40px' }}>
      <h2 style={{ marginBottom: '30px' }}>Admin Dashboard</h2>
      
      <div style={{ display: 'flex', gap: '20px', marginBottom: '20px' }}>
        <button 
          className="btn-primary" 
          style={{ opacity: activeTab === 'classes' ? 1 : 0.7 }}
          onClick={() => setActiveTab('classes')}
        >
          Manage Classes
        </button>
        <button 
          className="btn-primary" 
          style={{ opacity: activeTab === 'trainers' ? 1 : 0.7 }}
          onClick={() => setActiveTab('trainers')}
        >
          Manage Trainers
        </button>
        <button 
          className="btn-primary" 
          style={{ opacity: activeTab === 'members' ? 1 : 0.7 }}
          onClick={() => setActiveTab('members')}
        >
          Manage Members
        </button>
      </div>

      <div className="card">
        {activeTab === 'classes' && (
          <div>
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '20px' }}>
              <h3>Gym Classes</h3>
              <button className="btn-primary" style={{ backgroundColor: '#4caf50' }}>Add New Class</button>
            </div>
            <table style={{ width: '100%', textAlign: 'left', borderCollapse: 'collapse' }}>
              <thead>
                <tr style={{ borderBottom: '2px solid #333' }}>
                  <th style={{ padding: '10px 0' }}>Name</th>
                  <th>Category</th>
                  <th>Trainer</th>
                  <th>Spots</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {classes.map(c => (
                  <tr key={c.id} style={{ borderBottom: '1px solid #333' }}>
                    <td style={{ padding: '10px 0' }}>{c.name}</td>
                    <td>{c.category}</td>
                    <td>{c.trainerName}</td>
                    <td>{c.capacity - c.currentCount} / {c.capacity}</td>
                    <td>
                      <button style={{ color: 'var(--accent-blue)', marginRight: '10px' }}>Edit</button>
                      <button style={{ color: '#f44336' }} onClick={() => handleDeleteClass(c.id)}>Delete</button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}

        {activeTab === 'trainers' && (
          <div>
            <h3>Trainers Management</h3>
            <p style={{ color: 'var(--text-muted)' }}>Trainer list and management tools will go here.</p>
          </div>
        )}

        {activeTab === 'members' && (
          <div>
            <h3>Members Management</h3>
            <p style={{ color: 'var(--text-muted)' }}>Member list and management tools will go here.</p>
          </div>
        )}
      </div>
    </div>
  );
};

export default AdminDashboard;
