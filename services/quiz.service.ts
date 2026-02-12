import api from "./api";

export interface Quiz {
  id: number;
  studentClass: string;
  question: string;
  optionA: string;
  optionB: string;
  optionC: string;
  optionD: string;
  correctOption: "A" | "B" | "C" | "D";
  createdAt: string;
}

export interface CreateQuizRequest {
  studentClass: string;
  question: string;
  optionA: string;
  optionB: string;
  optionC: string;
  optionD: string;
  correctOption: "A" | "B" | "C" | "D";
}

// Get quizzes by class
const getQuizzes = async (studentClass: string): Promise<Quiz[]> => {
  const res = await api.get(`/admin/quizzes?class=${studentClass}`);
  return res.data;
};

// Create quiz
const createQuiz = async (data: CreateQuizRequest): Promise<void> => {
  await api.post("/admin/quizzes", data);
};

// Delete quiz
const deleteQuiz = async (id: number): Promise<void> => {
  await api.delete(`/admin/quizzes/${id}`);
};

export const quizService = {
  getQuizzes,
  createQuiz,
  deleteQuiz,
};
