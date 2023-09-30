import React from "react";
import ReactDOM from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";

// css
import "./index.css";

// layout
import Layout from "./Layout.tsx";

// pages
import Dashboard from "./pages/Dashboard.tsx";
import Products from "./pages/Products.tsx";
import Stocks from "./pages/Stocks.tsx";
import Suppliers from "./pages/Suppliers.tsx";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Layout />,
    children: [
      {
        path: "/",
        element: <Dashboard />,
      },
      {
        path: "/profile",
        element: "Profile",
      },
      {
        path: "/products",
        element: <Products />,
      },
      {
        path: "/stock",
        element: <Stocks />,
      },
      {
        path: "/suppliers",
        element: <Suppliers />,
      },
    ],
  },
]);

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
