import React, { useState } from 'react'
import { Outlet, useNavigate } from 'react-router-dom'
import { Menu, LogOut, User } from 'lucide-react'
import useAuthStore from '../store/authStore'
import authApi from '../api/authApi'
import Sidebar from '../components/Sidebar'

export default function DashboardLayout() {
  const navigate = useNavigate()
  const [sidebarOpen, setSidebarOpen] = useState(true)
  const { user, clearAuth } = useAuthStore()

  const handleLogout = async () => {
    try {
      await authApi.logout()
    } catch (error) {
      console.error('Logout error:', error)
    } finally {
      clearAuth()
      navigate('/login')
    }
  }

  const handleProfile = () => {
    navigate('/profile')
  }

  return (
    <div className="flex h-screen bg-gray-50">
      <Sidebar isOpen={sidebarOpen} />
      
      <div className="flex-1 flex flex-col">
        {/* Top Navigation */}
        <nav className="bg-white shadow-sm border-b border-gray-200">
          <div className="px-6 py-4 flex justify-between items-center">
            <button
              onClick={() => setSidebarOpen(!sidebarOpen)}
              className="p-2 hover:bg-gray-100 rounded-lg lg:hidden"
            >
              <Menu size={24} />
            </button>
            
            <div className="flex items-center gap-4">
              <div className="text-right">
                <p className="text-sm font-medium text-gray-900">
                  {user?.firstName} {user?.lastName}
                </p>
                <p className="text-xs text-gray-500">{user?.email}</p>
              </div>
              
              <button
                onClick={handleProfile}
                className="p-2 hover:bg-gray-100 rounded-lg"
                title="Profile"
              >
                <User size={20} className="text-gray-600" />
              </button>
              
              <button
                onClick={handleLogout}
                className="p-2 hover:bg-red-50 rounded-lg"
                title="Logout"
              >
                <LogOut size={20} className="text-red-600" />
              </button>
            </div>
          </div>
        </nav>

        {/* Main Content */}
        <main className="flex-1 overflow-auto">
          <Outlet />
        </main>
      </div>
    </div>
  )
}
