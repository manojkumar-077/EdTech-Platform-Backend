import api from "./api";

export interface LoginRequest {
  email: string;
  password: string;
  role: "ADMIN" | "STUDENT";
}

export interface LoginResponse {
  token: string;
  role: "ADMIN" | "STUDENT";
  userId: number;
}

const login = async (data: LoginRequest): Promise<LoginResponse> => {
  const response = await api.post("/auth/login", data);
  return response.data;
};
const forgotPassword = async (email: string): Promise<void> => {
  await api.post("/auth/forgot-password", { email });
};


const logout = () => {
  localStorage.removeItem("token");
  localStorage.removeItem("role");
  localStorage.removeItem("userId");
};

export const authService = {
  login,
  logout,
  forgotPassword,
};
