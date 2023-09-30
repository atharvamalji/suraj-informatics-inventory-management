import { Link } from "react-router-dom";

// icons
import AccountCircleIcon from "@mui/icons-material/AccountCircle";
import GroupsIcon from "@mui/icons-material/Groups";
import HomeIcon from "@mui/icons-material/Home";
import InventoryIcon from "@mui/icons-material/Inventory";
import WidgetsIcon from "@mui/icons-material/Widgets";

// other imports
import { ReactElement } from "react";
import { Outlet, Link as RouterLink } from "react-router-dom";

// types
type typeSidebarItem = {
  text: string;
  path: string;
  icon: ReactElement<any, any>;
};

export default function Layout() {
  return (
    <div className="flex h-screen">
      <Sidebar />
      <div className="flex-1">
        <div></div>
        <div>
          <Outlet />
        </div>
      </div>
    </div>
  );
}

const Sidebar = () => {
  const sidebarItemsList: Array<typeSidebarItem> = [
    {
      text: "Home",
      path: "/",
      icon: <HomeIcon />,
    },
    {
      text: "Profile",
      path: "/profile",
      icon: <AccountCircleIcon />,
    },
    {
      text: "Products",
      path: "/products",
      icon: <InventoryIcon />,
    },
    {
      text: "Stock",
      path: "/stock",
      icon: <WidgetsIcon />,
    },
    {
      text: "Suppliers",
      path: "/suppliers",
      icon: <GroupsIcon />,
    },
  ];

  return (
    <div className="w-[14rem] border-r">
      <div className="flex justify-center p-4">
        <span>
          <img src="./svg/logo.svg" className="h-6" />
        </span>
      </div>
      <hr />
      <nav>
        <ul className="space-y-1 p-1">
          {sidebarItemsList.map((item, index) => {
            return (
              <li
                key={`si-${index}`}
                className="hover:bg-purple-100 rounded-md transition ease-in-out duration-200"
              >
                <Link to={item.path}>
                  <div className="flex items-center p-1 px-2 space-x-2">
                    <span>{item.icon}</span>
                    <p>{item.text}</p>
                  </div>
                </Link>
              </li>
            );
          })}
        </ul>
      </nav>
    </div>
  );
};
