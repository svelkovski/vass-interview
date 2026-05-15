interface PageRequest {
  page?: number;
  size?: number;
}

interface DisplayEmployee {
  id: number;
  name: string;
  role: string;
  email: string;
  department: string;
  phoneNumber: string;
}

interface CreateEmployee {
  name: string;
  role: string;
  department: string;
  email: string;
}

const baseUrl: string = "http://localhost:8080/api/employees";

let employees: DisplayEmployee[] = [];

async function loadEmployees(params: PageRequest) {
  try {
    const response = await fetch(
      baseUrl + `?page=${params.page}&size=${params.size}`,
    );
    const data = await response.json();

    console.log(data);
    console.log(data.content);

    employees = data.content;

    renderEmployees(employees);
  } catch (err) {
    console.log(err);
  }
}

const filter = document.querySelector(".filter-input") as HTMLInputElement;

filter.addEventListener("input", () => {
  renderEmployees(employees, filter.value);
});

function renderEmployees(employees: DisplayEmployee[], filter?: string) {
  const grid = document.querySelector(".directory-grid");

  grid!.innerHTML = "";

  const filtered = filter
    ? employees.filter(
        (emp) =>
          emp.name.toLowerCase().includes(filter.toLowerCase()) ||
          emp.department.toLowerCase().includes(filter.toLowerCase()) ||
          emp.role.toLowerCase().includes(filter.toLowerCase()),
      )
    : employees;

  let profilePicIndex = 0;
  for (let emp of filtered) {
    profilePicIndex++;
    const container = document.createElement("div");
    container.className = "employee-card";
    container.innerHTML = `
              <div class="card-top">
                <div class="card-avatar-wrapper">
                  <img
                    src="https://randomuser.me/portraits/men/${profilePicIndex}.jpg"
                    alt="${emp.name}"
                    class="card-avatar"
                  />
                  <div class="status-dot"></div>
                </div>
                <h3 class="card-name">${emp.name}</h3>
                <p class="card-title">${emp.role}</p>
                <span class="department-badge">${emp.department}</span>
              </div>
              <div class="card-bottom">
                <div class="contact-row">
                  <div class="contact-icon">
                    <iconify-icon
                      icon="lucide:mail"
                      class="iconify-icon-extra-small"
                    ></iconify-icon>
                  </div>
                  <span class="contact-text">${emp.email}</span>
                </div>
                <div class="contact-row">
                  <div class="contact-icon">
                    <iconify-icon
                      icon="lucide:phone"
                      class="iconify-icon-extra-small"
                    ></iconify-icon>
                  </div>
                  <span class="contact-text">${emp.phoneNumber}</span>
                </div>
              </div>
              <div class="card-actions">
                <button class="card-btn">
                  <iconify-icon
                    icon="lucide:message-square"
                    class="iconify-icon-extra-small"
                  ></iconify-icon>
                  Message
                </button>
                <button class="card-btn">
                  <iconify-icon
                    icon="lucide:user"
                    class="iconify-icon-extra-small"
                  ></iconify-icon>
                  Profile
                </button>
              </div>
    `;

    grid!.append(container);
  }
}

async function createEmployee(newEmployee: CreateEmployee) {
  try {
    await fetch(baseUrl, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(newEmployee),
    });

    loadEmployees({ page: 0, size: 20 });
  } catch (err) {
    console.log(err);
  }
}

const form = document.getElementById("add-employee-form") as HTMLFormElement;

form!.addEventListener("submit", (e) => {
  e.preventDefault();

  const name = (
    document.getElementById("emp-name") as HTMLInputElement
  ).value.trim();
  const role = (
    document.getElementById("emp-role") as HTMLInputElement
  ).value.trim();
  const department = (
    document.getElementById("emp-dept") as HTMLInputElement
  ).value.trim();
  const email = (
    document.getElementById("emp-email") as HTMLInputElement
  ).value.trim();

  const newEmployee: CreateEmployee = {
    name: name,
    role: role,
    department: department,
    email: email,
  };

  createEmployee(newEmployee);
  form!.reset();
  document.getElementById("add-employee-modal")?.setAttribute("hidden", "");
});

loadEmployees({ page: 0, size: 20 });
