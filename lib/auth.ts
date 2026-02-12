// lib/auth.ts

const TOKEN_KEY = "token";
const ROLE_KEY = "role";
const USER_ID_KEY = "userId";

// Save auth data after login
export const saveAuthData = (
  token: string,
  role: "ADMIN" | "STUDENT",
  userId: number
) => {
  localStorage.setItem(TOKEN_KEY, token);
  localStorage.setItem(ROLE_KEY, role);
  localStorage.setItem(USER_ID_KEY, userId.toString());
};

// Get token
export const getToken = (): string | null => {
  return localStorage.getItem(TOKEN_KEY);
};

// Get role
export const getRole = (): "ADMIN" | "STUDENT" | null => {
  return localStorage.getItem(ROLE_KEY) as "ADMIN" | "STUDENT" | null;
};

// Get user id
export const getUserId = (): number | null => {
  const id = localStorage.getItem(USER_ID_KEY);
  return id ? Number(id) : null;
};

// Check login status
export const isLoggedIn = (): boolean => {
  return !!getToken();
};

// Clear auth data (logout)
export const clearAuthData = () => {
  localStorage.removeItem(TOKEN_KEY);
  localStorage.removeItem(ROLE_KEY);
  localStorage.removeItem(USER_ID_KEY);
};
