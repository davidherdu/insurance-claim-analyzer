import React, { useState } from 'react'
import axios from 'axios'

export default function ClaimForm() {
  const [form, setForm] = useState({
    policyNumber: '',
    description: '',
    amount: ''
  })

  const handleChange = e => {
    const { name, value } = e.target
    setForm(prev => ({ ...prev, [name]: value }))
  }

  const handleSubmit = async e => {
    e.preventDefault()
    await axios.post('/claims', {
      ...form,
      amount: parseFloat(form.amount)
    })
    setForm({ policyNumber: '', description: '', amount: '' })
  }

  return (
    <form onSubmit={handleSubmit}>
      <h2>Create New Claim</h2>
      <input name="policyNumber" placeholder="Policy #" value={form.policyNumber} onChange={handleChange} required />
      <input name="description" placeholder="Description" value={form.description} onChange={handleChange} required />
      <input name="amount" placeholder="Amount" type="number" value={form.amount} onChange={handleChange} required />
      <button type="submit">Submit</button>
    </form>
  )
}
