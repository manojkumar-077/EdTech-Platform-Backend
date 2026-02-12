import api from "./api";

export interface Student {
  id: number;
  name: string;
  email: string;
  studentClass: string;
  school: string;
  createdAt: string;
}

export interface CreateStudentRequest {
  name: string;
  email: string;
  studentClass: string;
  school: string;
}

// Get all students
const getStudents = async (): Promise<Student[]> => {
  const res = await api.get("/admin/students");
  return res.data;
};

// Create student
const createStudent = async (data: CreateStudentRequest): Promise<void> => {
  await api.post("/admin/students", data);
};

// Delete student
const deleteStudent = async (id: number): Promise<void> => {
  await api.delete(`/admin/students/${id}`);
};

export const studentService = {
  getStudents,
  createStudent,
  deleteStudent,
};
