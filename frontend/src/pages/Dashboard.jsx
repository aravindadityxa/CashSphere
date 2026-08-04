import React from 'react'
import useAuthStore from '../store/authStore'
import { BarChart3, Users, TrendingUp, AlertCircle } from 'lucide-react'

const StatCard = ({ title, value, icon: Icon, trend }) => (
  <div className="bg-white rounded-lg shadow p-6">
    <div className="flex items-center justify-between">
      <div>
        <p className="text-gray-500 text-sm font-medium">{title}</p>
        <p className="text-2xl font-bold text-gray-900 mt-2">{value}</p>
        {trend && (
          <p className="text-sm text-green-600 mt-2">↑ {trend} from last month</p>
        )}
      </div>
      <div className="bg-primary-100 p-3 rounded-lg">
        <Icon size={24} className="text-primary-600" />
      </div>
    </div>
  </div>
)

export default function Dashboard() {
  const user = useAuthStore((state) => state.user)

  return (
    <div className="p-8">
      {/* Welcome Section */}
      <div className="mb-8">
        <h1 className="text-3xl font-bold text-gray-900">
          Welcome back, {user?.firstName}!
        </h1>
        <p className="text-gray-500 mt-2">
          Here's your treasury dashboard overview
        </p>
      </div>

      {/* Stats Grid */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
        <StatCard
          title="Total Cash Position"
          value="$2.5M"
          icon={TrendingUp}
          trend="12%"
        />
        <StatCard
          title="Active Accounts"
          value="24"
          icon={Users}
          trend="3"
        />
        <StatCard
          title="Pending Payments"
          value="8"
          icon={AlertCircle}
        />
        <StatCard
          title="Liquidity Ratio"
          value="85%"
          icon={BarChart3}
          trend="5%"
        />
      </div>

      {/* Main Content Area */}
      <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
        {/* Cash Flow Chart */}
        <div className="lg:col-span-2 bg-white rounded-lg shadow p-6">
          <h2 className="text-lg font-semibold text-gray-900 mb-4">
            Cash Flow (Last 7 Days)
          </h2>
          <div className="h-64 flex items-center justify-center bg-gray-50 rounded-lg">
            <p className="text-gray-500">Chart placeholder - Charts module coming next</p>
          </div>
        </div>

        {/* Recent Payments */}
        <div className="bg-white rounded-lg shadow p-6">
          <h2 className="text-lg font-semibold text-gray-900 mb-4">
            Recent Payments
          </h2>
          <div className="space-y-3">
            {[1, 2, 3].map((i) => (
              <div key={i} className="flex items-center justify-between pb-3 border-b border-gray-200 last:border-b-0">
                <div>
                  <p className="text-sm font-medium text-gray-900">Payment #{i}</p>
                  <p className="text-xs text-gray-500">2 hours ago</p>
                </div>
                <p className="text-sm font-semibold text-gray-900">$50,000</p>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  )
}
