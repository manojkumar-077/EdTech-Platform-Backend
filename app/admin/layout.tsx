import AdminSidebar from "@/components/common/AdminSidebar";
import AdminTopbar from "@/components/common/AdminTopbar";

export default function AdminLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <div className="flex">
      {/* Sidebar */}
      <AdminSidebar />

      {/* Main content */}
      <div className="flex-1 min-h-screen bg-gray-100">
        <AdminTopbar />
        <main className="p-6">{children}</main>
      </div>
    </div>
  );
}
