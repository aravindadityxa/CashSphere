import React from 'react'
import { useLocation, Link } from 'react-router-dom'
import { BarChart3, Home, FileText, Settings } from 'lucide-react'

const menuItems = [
  { name: 'Dashboard', icon: Home, href: '/dashboard' },
  { name: 'Reports', icon: FileText, href: '/reports' },
  { name: 'Analytics', icon: BarChart3, href: '/analytics' },
  { name: 'Settings', icon: Settings, href: '/settings' }
]

export default function Sidebar({ isOpen }) {
  const location = useLocation()

  return (
    <aside className={`
      w-64 bg-gray-900 text-white transition-transform duration-200 ease-in-out
      ${isOpen ? 'translate-x-0' : '-translate-x-full'}
      fixed lg:relative h-screen z-20 lg:z-0
    `}>
      <div className="p-6">
        <h1 className="text-2xl font-bold text-primary-400">CashSphere</h1>
        <p className="text-xs text-gray-400 mt-1">Treasury & Cash Management</p>
      </div>

      <nav className="mt-8 px-4 space-y-2">
        {menuItems.map((item) => {
          const Icon = item.icon
          const isActive = location.pathname === item.href
          
          return (
            <Link
              key={item.name}
              to={item.href}
              className={`
                flex items-center gap-3 px-4 py-3 rounded-lg transition-colors
                ${isActive
                  ? 'bg-primary-600 text-white'
                  : 'text-gray-300 hover:bg-gray-800'
                }
              `}
            >
              <Icon size={20} />
              <span className="text-sm font-medium">{item.name}</span>
            </Link>
          )
        })}
      </nav>
    </aside>
  )
}
