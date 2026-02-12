"use client";

import { useEffect } from "react";
import { useRouter } from "next/navigation";
import AdminSidebar from "@/components/common/AdminSidebar";
import AdminTopbar from "@/components/common/AdminTopbar";
import { getRole, isLoggedIn } from "@/lib/auth";

export default function AdminLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  const router = useRouter();

  useEffect(() => {
    if (!isLoggedIn()) {
      router.replace("/login");
      return;
    }

    const role = getRole();
    if (role !== "ADMIN") {
      router.replace("/student/quizzes");
    }
  }, [router]);

  return (
    <div className="flex">
      <AdminSidebar />
      <div className="flex-1 min-h-screen bg-gray-100">
        <AdminTopbar />
        <main className="p-6">{children}</main>
      </div>
    </div>
  );
}
