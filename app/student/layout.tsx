"use client";

import { useEffect } from "react";
import { useRouter } from "next/navigation";
import StudentSidebar from "@/components/common/StudentSidebar";
import { getRole, isLoggedIn } from "@/lib/auth";

export default function StudentLayout({
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
    if (role !== "STUDENT") {
      router.replace("/admin/dashboard");
    }
  }, [router]);

  return (
    <div className="flex">
      <StudentSidebar />
      <main className="flex-1 p-6 bg-gray-100 min-h-screen">
        {children}
      </main>
    </div>
  );
}
