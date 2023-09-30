import axios from "axios";
import { useState, useEffect } from "react";

const apiBase = "http://localhost:8080/api/";

const Suppliers = () => {
  const [suppliers, setSuppliers] = useState();

  useEffect(() => {
    axios.get(`${apiBase}suppliers`).then((res) => {
      setSuppliers(res.data);
    });
  }, []);

  return (
    <div className="p-4 space-y-4">
      <div>
        <h4 className="text-3xl font-bold">Suppliers</h4>
      </div>
      <div>
        <div className="space-y-2">
          <div className="border rounded shadow-sm">
            <table className="w-full table-auto border-collapse border-hidden">
              <thead>
                <tr>
                  <th className="p-1 border w-8">#</th>
                  <th className="p-1 border">Name</th>
                  <th className="p-1 border">Location</th>
                  <th className="p-1 border">Actions</th>
                </tr>
              </thead>
              <tbody className="[&>*:nth-child(even)]:bg-slate-50">
                {suppliers
                  ? suppliers.map((supplier, index) => {
                      return (
                        <tr key={`tb-${index}`}>
                          <td className="p-1 border">{index}</td>
                          <td className="p-1 border">{supplier.name}</td>
                          <td className="p-1 border">{supplier.location}</td>
                          <td className="p-1 border">edit delete</td>
                        </tr>
                      );
                    })
                  : "loading"}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Suppliers;
