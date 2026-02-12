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


// ================= STUDENT SIDE =================

export interface StudentQuiz {
  id: number;
  questionCount: number;
  studentClass: string;
}

export interface QuizQuestion {
  id: number;
  question: string;
  optionA: string;
  optionB: string;
  optionC: string;
  optionD: string;
}

export interface SubmitQuizRequest {
  quizId: number;
  answers: {
    questionId: number;
    selectedOption: "A" | "B" | "C" | "D";
  }[];
}

export interface QuizResult {
  totalQuestions: number;
  correctAnswers: number;
  score: number;
}

// Get available quizzes
const getStudentQuizzes = async (): Promise<StudentQuiz[]> => {
  const res = await api.get("/student/quizzes");
  return res.data;
};

// Start quiz
const startQuiz = async (quizId: number): Promise<QuizQuestion[]> => {
  const res = await api.post(`/student/quizzes/${quizId}/start`);
  return res.data;
};

// Submit quiz
const submitQuiz = async (
  data: SubmitQuizRequest
): Promise<QuizResult> => {
  const res = await api.post("/student/quizzes/submit", data);
  return res.data;
};

export const quizService = {
  // admin
  getQuizzes,
  createQuiz,
  deleteQuiz,

  // student
  getStudentQuizzes,
  startQuiz,
  submitQuiz,
};
