import axios from "axios";
import { useEffect, useState } from "react";

const apiBase = "http://localhost:8080/api/";

const Dashboard = () => {
  const [orders, setOrders] = useState();
  const [products, setProducts] = useState();
  const [suppliers, setSuppliers] = useState();
  const [stocks, setStocks] = useState();

  useEffect(() => {
    axios.get(`${apiBase}productOrders`).then((res) => {
      setOrders(res.data);
    })
    axios.get(`${apiBase}products`).then((res) => {
      setProducts(res.data);
    })
    axios.get(`${apiBase}suppliers`).then((res) => {
      setSuppliers(res.data);
    })
    axios.get(`${apiBase}stocks`).then((res) => {
      setStocks(res.data);
    })
  }, []);

  return (
    <div className="p-4 space-y-4">
      <div>
        <h4 className="text-3xl font-bold">Dashboard</h4>
      </div>
      <div>
        <div className="grid grid-cols-4 gap-4">
          <div className="text-stone-800 bg-white p-4 border rounded shadow-sm">
            <p>Total orders</p>
            <p className="text-5xl font-extrabold">{orders ? orders.length : 0}</p>
          </div>
          <div className="text-stone-800 bg-white p-4 border rounded shadow-sm">
            <p>Total products</p>
            <p className="text-5xl font-extrabold">{products ? products.length : 0}</p>
          </div>
          <div className="text-stone-800 bg-white p-4 border rounded shadow-sm">
            <p>Total suppliers</p>
            <p className="text-5xl font-extrabold">{suppliers ? suppliers.length : 0}</p>
          </div>
          <div className="text-stone-800 bg-white p-4 border rounded shadow-sm">
            <p>Total stocks</p>
            <p className="text-5xl font-extrabold">{stocks ? stocks.length : 0}</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
