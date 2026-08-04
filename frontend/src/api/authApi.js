import axiosInstance from './axios'

const authApi = {
  register: async (data) => {
    const response = await axiosInstance.post('/v1/auth/register', data)
    return response.data
  },

  login: async (email, password) => {
    const response = await axiosInstance.post('/v1/auth/login', {
      email,
      password
    })
    return response.data
  },

  logout: async () => {
    const response = await axiosInstance.post('/v1/auth/logout')
    return response.data
  },

  getProfile: async () => {
    const response = await axiosInstance.get('/v1/auth/profile')
    return response.data
  },

  refreshToken: async (refreshToken) => {
    const response = await axiosInstance.post('/v1/auth/refresh-token', {
      refreshToken
    })
    return response.data
  }
}

export default authApi
