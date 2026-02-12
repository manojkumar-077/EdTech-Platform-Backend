import api from "./api";

export interface Admin {
  id: number;
  email: string;
  role: string;
  createdAt: string;
}

export interface CreateAdminRequest {
  email: string;
  password: string;
  role: string;
}

const getDashboardStats = async (): Promise<DashboardStats> => {
  const res = await api.get("/admin/dashboard/stats");
  return res.data;
};

// Get all admins
const getAdmins = async (): Promise<Admin[]> => {
  const res = await api.get("/admin/admins");
  return res.data;
};

// Create admin
const createAdmin = async (data: CreateAdminRequest): Promise<void> => {
  await api.post("/admin/admins", data);
};

// Delete admin
const deleteAdmin = async (id: number): Promise<void> => {
  await api.delete(`/admin/admins/${id}`);
};

export const adminService = {
  // dashboard
  getDashboardStats,

  // admin management
  getAdmins,
  createAdmin,
  deleteAdmin,
};

export interface DashboardStats {
  totalStudents: number;
  totalAdmins: number;
  totalSubjects: number;
  totalQuizzes: number;
}
