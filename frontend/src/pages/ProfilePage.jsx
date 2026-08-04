import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import useAuthStore from '../store/authStore'
import { ArrowLeft, Mail, User, Calendar } from 'lucide-react'

export default function ProfilePage() {
  const navigate = useNavigate()
  const user = useAuthStore((state) => state.user)
  const [isEditing, setIsEditing] = useState(false)

  const handleGoBack = () => {
    navigate('/dashboard')
  }

  return (
    <div className="p-8">
      <button
        onClick={handleGoBack}
        className="flex items-center gap-2 text-primary-600 hover:text-primary-700 mb-8"
      >
        <ArrowLeft size={20} />
        Back to Dashboard
      </button>

      <div className="max-w-2xl">
        <div className="bg-white rounded-lg shadow-lg overflow-hidden">
          {/* Header */}
          <div className="bg-gradient-to-r from-primary-600 to-primary-700 px-8 py-12">
            <div className="flex items-center gap-4">
              <div className="w-20 h-20 bg-white rounded-full flex items-center justify-center">
                <User size={40} className="text-primary-600" />
              </div>
              <div>
                <h1 className="text-3xl font-bold text-white">
                  {user?.firstName} {user?.lastName}
                </h1>
                <p className="text-primary-100">{user?.email}</p>
              </div>
            </div>
          </div>

          {/* Content */}
          <div className="p-8">
            <div className="space-y-6">
              {/* Username */}
              <div className="border-b border-gray-200 pb-6">
                <label className="block text-sm font-medium text-gray-700 mb-2">
                  Username
                </label>
                <div className="flex items-center gap-2">
                  <User size={18} className="text-gray-400" />
                  <p className="text-lg text-gray-900">{user?.username}</p>
                </div>
              </div>

              {/* Email */}
              <div className="border-b border-gray-200 pb-6">
                <label className="block text-sm font-medium text-gray-700 mb-2">
                  Email Address
                </label>
                <div className="flex items-center gap-2">
                  <Mail size={18} className="text-gray-400" />
                  <p className="text-lg text-gray-900">{user?.email}</p>
                </div>
              </div>

              {/* Roles */}
              <div className="border-b border-gray-200 pb-6">
                <label className="block text-sm font-medium text-gray-700 mb-2">
                  Roles
                </label>
                <div className="flex flex-wrap gap-2">
                  {user?.roles?.map((role) => (
                    <span
                      key={role}
                      className="px-3 py-1 bg-primary-100 text-primary-700 rounded-full text-sm font-medium"
                    >
                      {role}
                    </span>
                  ))}
                </div>
              </div>

              {/* Member Since */}
              <div className="pb-6">
                <label className="block text-sm font-medium text-gray-700 mb-2">
                  Member Since
                </label>
                <div className="flex items-center gap-2">
                  <Calendar size={18} className="text-gray-400" />
                  <p className="text-lg text-gray-900">
                    {new Date(user?.createdAt).toLocaleDateString('en-US', {
                      year: 'numeric',
                      month: 'long',
                      day: 'numeric'
                    })}
                  </p>
                </div>
              </div>

              {/* Last Login */}
              {user?.lastLogin && (
                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-2">
                    Last Login
                  </label>
                  <p className="text-lg text-gray-900">
                    {new Date(user?.lastLogin).toLocaleDateString('en-US', {
                      year: 'numeric',
                      month: 'long',
                      day: 'numeric',
                      hour: '2-digit',
                      minute: '2-digit'
                    })}
                  </p>
                </div>
              )}
            </div>

            {/* Actions */}
            <div className="mt-8 pt-6 border-t border-gray-200 flex gap-3">
              <button
                onClick={() => setIsEditing(!isEditing)}
                className="px-6 py-2 bg-primary-600 text-white rounded-lg hover:bg-primary-700 transition font-medium"
              >
                {isEditing ? 'Save Changes' : 'Edit Profile'}
              </button>
              <button
                onClick={() => setIsEditing(false)}
                className="px-6 py-2 bg-gray-200 text-gray-900 rounded-lg hover:bg-gray-300 transition font-medium"
              >
                Cancel
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}
