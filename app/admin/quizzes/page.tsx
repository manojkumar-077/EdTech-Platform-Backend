"use client";

import { useEffect, useState } from "react";
import { quizService, Quiz } from "@/services/quiz.service";
import AddQuizModal from "@/components/modals/AddQuizModal";

export default function QuizManagementPage() {
  const [studentClass, setStudentClass] = useState("10");
  const [quizzes, setQuizzes] = useState<Quiz[]>([]);
  const [loading, setLoading] = useState(true);
  const [showModal, setShowModal] = useState(false);

  const loadQuizzes = async () => {
    try {
      const res = await quizService.getQuizzes(studentClass);
      setQuizzes(res);
    } catch {
      alert("Failed to load quizzes");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadQuizzes();
  }, [studentClass]);

  const handleDelete = async (id: number) => {
    if (!confirm("Delete this quiz?")) return;
    await quizService.deleteQuiz(id);
    loadQuizzes();
  };

  return (
    <div>
      <div className="flex justify-between mb-6">
        <h1 className="text-2xl font-bold">Quiz Management</h1>

        <div className="flex gap-3">
          <select
            value={studentClass}
            onChange={(e) => setStudentClass(e.target.value)}
            className="border p-2"
          >
            <option value="8">Class 8</option>
            <option value="9">Class 9</option>
            <option value="10">Class 10</option>
            <option value="11">Class 11</option>
            <option value="12">Class 12</option>
          </select>

          <button
            className="bg-black text-white px-4 py-2 rounded"
            onClick={() => setShowModal(true)}
          >
            Add Quiz
          </button>
        </div>
      </div>

      {loading ? (
        <p>Loading...</p>
      ) : (
        <div className="space-y-4">
          {quizzes.map((quiz) => (
            <div
              key={quiz.id}
              className="bg-white p-4 rounded shadow"
            >
              <p className="font-semibold mb-2">
                {quiz.question}
              </p>

              <ul className="ml-4 text-sm">
                <li>A. {quiz.optionA}</li>
                <li>B. {quiz.optionB}</li>
                <li>C. {quiz.optionC}</li>
                <li>D. {quiz.optionD}</li>
              </ul>

              <p className="text-sm mt-2 text-green-600">
                Correct: {quiz.correctOption}
              </p>

              <button
                className="text-red-600 text-sm mt-2"
                onClick={() => handleDelete(quiz.id)}
              >
                Delete
              </button>
            </div>
          ))}
        </div>
      )}

      {showModal && (
        <AddQuizModal
          studentClass={studentClass}
          onClose={() => setShowModal(false)}
          onSuccess={loadQuizzes}
        />
      )}
    </div>
  );
}
