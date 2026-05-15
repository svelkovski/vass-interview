interface Page<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
  first: boolean;
  last: boolean;
  empty: boolean;
}

interface PageRequest {
  page?: number;
  size?: number;
}

interface DisplayEmployee {
  id: number;
  name: string;
  lastName: string;
  email: string;
  department: string;
  phoneNumber: string;
}

interface CreateEmployee {
  name: string;
  lastName: string;
  email: string;
  department: string;
  phoneNumber: string;
}

const baseUrl: string = "http://localhost:8080/api/employees";

const queryParams = "";

async function loadEmployees(params: PageRequest) {
  try {
    const response = await fetch(
      baseUrl + `?page=${params.page}&size=${params.size}`,
    );
    const data = await response.json();

    console.log(data);
  } catch (err) {
    console.log(err);
  }
}

loadEmployees({ page: 0, size: 20 });
