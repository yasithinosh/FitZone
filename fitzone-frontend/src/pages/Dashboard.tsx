import React, { useEffect, useState } from 'react';
import { useAuth } from '../context/AuthContext';
import api from '../utils/api';

const Dashboard: React.FC = () => {
  const { user } = useAuth();
  const [data, setData] = useState<any>(null);

  useEffect(() => {
    const fetchDashboard = async () => {
      try {
        if (user?.id) {
          const res = await api.get(`/members/${user.id}/dashboard`);
          if (res.data.success) {
            setData(res.data.data);
          }
        }
      } catch (err) {
        console.error('Failed to fetch dashboard', err);
      }
    };
    fetchDashboard();
  }, [user]);

  if (!data) return <div className="container" style={{ paddingTop: '40px' }}>Loading...</div>;

  return (
    <div className="container" style={{ paddingTop: '40px' }}>
      <h2 style={{ marginBottom: '30px' }}>Member Dashboard</h2>
      
      <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(200px, 1fr))', gap: '20px', marginBottom: '30px' }}>
        <div className="card" style={{ textAlign: 'center' }}>
          <h3>Membership</h3>
          <p style={{ fontSize: '1.5rem', color: 'var(--primary-color)' }}>{data.membershipType}</p>
        </div>
        <div className="card" style={{ textAlign: 'center' }}>
          <h3>Total Bookings</h3>
          <p style={{ fontSize: '1.5rem', color: 'var(--primary-color)' }}>{data.totalBookings}</p>
        </div>
      </div>

      <div className="card">
        <h3>Upcoming Classes</h3>
        {data.upcomingBookings && data.upcomingBookings.length > 0 ? (
          <ul style={{ listStyle: 'none', padding: 0, marginTop: '20px' }}>
            {data.upcomingBookings.map((b: any) => (
              <li key={b.id} style={{ padding: '10px 0', borderBottom: '1px solid #333' }}>
                {b.className} with {b.trainerName} - {b.bookingDate} at {b.startTime}
              </li>
            ))}
          </ul>
        ) : (
          <p style={{ color: 'var(--text-muted)', marginTop: '10px' }}>No upcoming classes booked.</p>
        )}
      </div>
    </div>
  );
};

export default Dashboard;
