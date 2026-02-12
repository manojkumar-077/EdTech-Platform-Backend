"use client";

import Link from "next/link";
import { usePathname } from "next/navigation";

const menuItems = [
  { label: "Dashboard", path: "/admin/dashboard" },
  { label: "Admins", path: "/admin/admins" },
  { label: "Students", path: "/admin/students" },
  { label: "Syllabus", path: "/admin/syllabus" },
  { label: "Quizzes", path: "/admin/quizzes" },
];

export default function AdminSidebar() {
  const pathname = usePathname();

  return (
    <aside className="w-64 bg-black text-white min-h-screen p-4">
      <h2 className="text-xl font-bold mb-8">Admin Panel</h2>

      <nav className="space-y-2">
        {menuItems.map((item) => (
          <Link
            key={item.path}
            href={item.path}
            className={`block px-4 py-2 rounded ${
              pathname === item.path
                ? "bg-gray-800"
                : "hover:bg-gray-700"
            }`}
          >
            {item.label}
          </Link>
        ))}
      </nav>
    </aside>
  );
}
