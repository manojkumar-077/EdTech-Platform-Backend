"use client";

import Link from "next/link";
import { usePathname } from "next/navigation";

export default function StudentSidebar() {
  const pathname = usePathname();

  return (
    <aside className="w-64 bg-black text-white min-h-screen p-4">
      <h2 className="text-xl font-bold mb-8">Student Panel</h2>

      <nav className="space-y-2">
        <Link
          href="/student/quizzes"
          className={`block px-4 py-2 rounded ${
            pathname.startsWith("/student/quizzes")
              ? "bg-gray-800"
              : "hover:bg-gray-700"
          }`}
        >
          Quizzes
        </Link>

        <Link
          href="/student/materials"
          className={`block px-4 py-2 rounded ${
            pathname.startsWith("/student/materials")
              ? "bg-gray-800"
              : "hover:bg-gray-700"
          }`}
        >
          Study Materials
        </Link>
      </nav>
    </aside>
  );
}
