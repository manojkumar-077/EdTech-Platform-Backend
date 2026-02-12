"use client";

import { useEffect, useState } from "react";
import { adminService, Admin } from "@/services/admin.service";
import AddAdminModal from "@/components/modals/AddAdminModal";

export default function AdminManagementPage() {
  const [admins, setAdmins] = useState<Admin[]>([]);
  const [loading, setLoading] = useState(true);
  const [showModal, setShowModal] = useState(false);

  const fetchAdmins = async () => {
    try {
      const res = await adminService.getAdmins();
      setAdmins(res);
    } catch (err) {
      console.error("Failed to fetch admins");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchAdmins();
  }, []);

  const handleDelete = async (id: number) => {
    if (!confirm("Delete this admin?")) return;
    await adminService.deleteAdmin(id);
    fetchAdmins();
  };

  return (
    <div>
      <div className="flex justify-between mb-6">
        <h1 className="text-2xl font-bold">Admin Management</h1>
        <button
          className="bg-black text-white px-4 py-2 rounded"
          onClick={() => setShowModal(true)}
        >
          Add Admin
        </button>
      </div>

      {loading ? (
        <p>Loading...</p>
      ) : (
        <table className="w-full bg-white rounded shadow">
          <thead>
            <tr className="border-b">
              <th className="p-3 text-left">Email</th>
              <th className="p-3 text-left">Role</th>
              <th className="p-3 text-left">Created</th>
              <th className="p-3 text-left">Action</th>
            </tr>
          </thead>
          <tbody>
            {admins.map((admin) => (
              <tr key={admin.id} className="border-b">
                <td className="p-3">{admin.email}</td>
                <td className="p-3">{admin.role}</td>
                <td className="p-3">{admin.createdAt}</td>
                <td className="p-3">
                  <button
                    className="text-red-600"
                    onClick={() => handleDelete(admin.id)}
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
        <AddAdminModal
          onClose={() => setShowModal(false)}
          onSuccess={fetchAdmins}
        />
      )}
    </div>
  );
}
