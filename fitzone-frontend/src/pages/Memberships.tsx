import React, { useEffect, useState } from 'react';
import api from '../utils/api';

interface Membership {
  id: number;
  type: string;
  price: number;
  durationDays: number;
  features: string;
}

const Memberships: React.FC = () => {
  const [memberships, setMemberships] = useState<Membership[]>([]);

  useEffect(() => {
    const fetchMemberships = async () => {
      try {
        const res = await api.get('/memberships');
        if (res.data.success) {
          setMemberships(res.data.data);
        }
      } catch (error) {
        console.error('Failed to fetch memberships', error);
      }
    };
    fetchMemberships();
  }, []);

  const parseFeatures = (featuresStr: string) => {
    try {
      let parsed = JSON.parse(featuresStr);
      if (typeof parsed === 'string') {
        parsed = JSON.parse(parsed);
      }
      return Array.isArray(parsed) ? parsed : [featuresStr];
    } catch {
      return [featuresStr];
    }
  };

  return (
    <div className="container" style={{ paddingTop: '40px' }}>
      <h2 style={{ marginBottom: '30px', textAlign: 'center' }}>Membership Plans</h2>
      <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fill, minmax(300px, 1fr))', gap: '20px' }}>
        {memberships.map(m => (
          <div key={m.id} className="card" style={{ textAlign: 'center' }}>
            <h3>{m.type}</h3>
            <div style={{ fontSize: '2rem', fontWeight: 'bold', margin: '20px 0', color: 'var(--primary-color)' }}>
              ${m.price}
            </div>
            <p style={{ color: 'var(--text-muted)' }}>Valid for {m.durationDays} days</p>
            <ul style={{ listStyle: 'none', padding: 0, marginTop: '20px', textAlign: 'left' }}>
              {parseFeatures(m.features).map((feature, idx) => (
                <li key={idx} style={{ padding: '8px 0', borderBottom: '1px solid #333' }}>
                  ✓ {feature}
                </li>
              ))}
            </ul>
            <button className="btn-primary" style={{ marginTop: '20px', width: '100%' }}>Select Plan</button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Memberships;
