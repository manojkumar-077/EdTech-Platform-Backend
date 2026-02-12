"use client";

import { useEffect, useState } from "react";
import { quizService, StudentQuiz } from "@/services/quiz.service";
import { useRouter } from "next/navigation";

export default function StudentQuizzesPage() {
  const [quizzes, setQuizzes] = useState<StudentQuiz[]>([]);
  const router = useRouter();

  useEffect(() => {
    quizService.getStudentQuizzes().then(setQuizzes);
  }, []);

  return (
    <div>
      <h1 className="text-2xl font-bold mb-6">Available Quizzes</h1>

      <div className="grid gap-4">
        {quizzes.map((quiz) => (
          <div
            key={quiz.id}
            className="bg-white p-4 rounded shadow flex justify-between items-center"
          >
            <div>
              <p className="font-semibold">
                Class {quiz.studentClass} Quiz
              </p>
              <p className="text-sm text-gray-500">
                {quiz.questionCount} Questions
              </p>
            </div>

            <button
              className="bg-black text-white px-4 py-2 rounded"
              onClick={() =>
                router.push(`/student/quizzes/${quiz.id}`)
              }
            >
              Start Quiz
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}
