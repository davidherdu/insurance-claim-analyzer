import React from 'react'
import ClaimList from './components/ClaimList'
import ClaimForm from './components/ClaimForm'

export default function App() {
  return (
    <div style={{ padding: 20 }}>
      <h1>Insurance Claims</h1>
      <ClaimForm />
      <hr />
      <ClaimList />
    </div>
  )
}
