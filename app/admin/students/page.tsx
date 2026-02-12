"use client";

import { useEffect, useState } from "react";
import { studentService, Student } from "@/services/student.service";
import AddStudentModal from "@/components/modals/AddStudentModal";

export default function StudentOnboardingPage() {
  const [students, setStudents] = useState<Student[]>([]);
  const [loading, setLoading] = useState(true);
  const [showModal, setShowModal] = useState(false);

  const fetchStudents = async () => {
    try {
      const res = await studentService.getStudents();
      setStudents(res);
    } catch (err) {
      console.error("Failed to fetch students");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchStudents();
  }, []);

  const handleDelete = async (id: number) => {
    if (!confirm("Delete this student?")) return;
    await studentService.deleteStudent(id);
    fetchStudents();
  };

  return (
    <div>
      <div className="flex justify-between mb-6">
        <h1 className="text-2xl font-bold">Student Onboarding</h1>
        <button
          className="bg-black text-white px-4 py-2 rounded"
          onClick={() => setShowModal(true)}
        >
          Add Student
        </button>
      </div>

      {loading ? (
        <p>Loading...</p>
      ) : (
        <table className="w-full bg-white rounded shadow">
          <thead>
            <tr className="border-b">
              <th className="p-3 text-left">Name</th>
              <th className="p-3 text-left">Email</th>
              <th className="p-3 text-left">Class</th>
              <th className="p-3 text-left">School</th>
              <th className="p-3 text-left">Created</th>
              <th className="p-3 text-left">Action</th>
            </tr>
          </thead>
          <tbody>
            {students.map((student) => (
              <tr key={student.id} className="border-b">
                <td className="p-3">{student.name}</td>
                <td className="p-3">{student.email}</td>
                <td className="p-3">{student.studentClass}</td>
                <td className="p-3">{student.school}</td>
                <td className="p-3">{student.createdAt}</td>
                <td className="p-3">
                  <button
                    className="text-red-600"
                    onClick={() => handleDelete(student.id)}
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}

      {showModal && (
        <AddStudentModal
          onClose={() => setShowModal(false)}
          onSuccess={fetchStudents}
        />
      )}
    </div>
  );
}
