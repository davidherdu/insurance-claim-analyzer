import React, { useEffect, useState } from 'react'
import axios from 'axios'

export default function ClaimList() {
  const [claims, setClaims] = useState([])

  useEffect(() => {
    axios.get('/claims').then(res => setClaims(res.data))
  }, [])

  return (
    <div>
      <h2>All Claims</h2>
      <ul>
        {claims.map(c => (
          <li key={c.id}>
            <b>{c.policyNumber}</b> - €{c.amount} - {c.description}
          </li>
        ))}
      </ul>
    </div>
  )
}
