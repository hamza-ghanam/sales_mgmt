# Sales System
A simple sales system, this system includes products management, client's management and sales operations management.

## About Sales System
It's a simple Spring Boot web service (RESTful) for managing sales system, so you can: 
1. Manage Products
2. Manage Clients.
3. Manage Sales.
4. It has some logging functionality to track all update operations on sale transactions.

## Installation

First of all, You should download the source code for this project and run it on your local machine, you may clone its Git repository and install its dependencies:

```bash
git clone https://github.com/hamza-ghanam/sales_mgmt.git
```

Of course you shoud have JDK 1.8 with MySQL >= 5.5 already installed on your PC.


## Prepping The Database

Now, you need to create a MySQL database, called "sales_db", so you should have MySQL DB Engine >= 5.5 installed and ready then run the commands:

```bash
mysql -u db_user -p
CREATE DATABASE sales_db CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
GRANT ALL PRIVILEGES ON sales_db.* TO 'db_user'@'localhost'; 
USE sales_db;
```
Where ***db_user*** is a user exists in MySQL.

Import the database SQL file if you want some demo data.

```bash
sales_db < sales_db.sql
```

Now, edit your application.properties file to set spring.datasource.username with your db_user and spring.datasource.password with its password. 

## Usage

### **Category Management**

1. List all categories

Method: GET
```url
http://localhost:8080/api/v1/categories
```
Sample Output:
```json
[
    {
        "id": 1,
        "name": "Milk and Dairy",
        "productList": [
            {
                "id": 1,
                "name": "Mozarella Cheese",
                "description": "Italian Mozarella Cheese",
                "price": 250.00,
                "createdAt": "2020-03-26T12:53:00.000+0000"
            },
            {
                "id": 4,
                "name": "Full Fat Milk",
                "description": "Full Fat Milk",
                "price": 100.00,
                "createdAt": "2020-03-27T15:09:53.000+0000"
            },
            {
                "id": 29,
                "name": "Parmesan Cheese",
                "description": "Italian Parmesan Cheese",
                "price": 250.00,
                "createdAt": "2020-03-27T19:02:18.000+0000"
            },
            {
                "id": 32,
                "name": "Cow Yogurt",
                "description": "Produced by bacterial fermentation of milk. The bacteria used to make yogurt are known as yogurt cultures.",
                "price": 150.00,
                "createdAt": "2020-03-27T19:43:44.000+0000"
            },
            {
                "id": 33,
                "name": "Goat Yogurt",
                "description": "Produced by bacterial fermentation of milk. The bacteria used to make yogurt are known as yogurt cultures.",
                "price": 200.00,
                "createdAt": "2020-03-27T19:47:20.000+0000"
            }
        ]
    },
    {
        "id": 2,
        "name": "Meats",
        "productList": [
            {
                "id": 2,
                "name": "Beef Steak",
                "description": "Deicious Beef Steak",
                "price": 500.00,
                "createdAt": "2020-03-26T12:54:00.000+0000"
            },
            {
                "id": 31,
                "name": "Chicken Rosto",
                "description": "Pure chicken breast with pistachio stuffed in a net, naturally smoked with the finest types of wood.",
                "price": 300.00,
                "createdAt": "2020-03-27T19:28:51.000+0000"
            }
        ]
    }
]
```
2. List category by id (with its products):

Method: GET
```url
http://localhost:8080/api/v1/categories/1
```
Sample Output:
```json
{
    "id": 1,
    "name": "Milk and Dairy",
    "productList": [
        {
            "id": 1,
            "name": "Mozarella Cheese",
            "description": "Italian Mozarella Cheese",
            "price": 250.00,
            "createdAt": "2020-03-26T12:53:00.000+0000"
        },
        {
            "id": 4,
            "name": "Full Fat Milk",
            "description": "Full Fat Milk",
            "price": 100.00,
            "createdAt": "2020-03-27T15:09:53.000+0000"
        },
        {
            "id": 29,
            "name": "Parmesan Cheese",
            "description": "Italian Parmesan Cheese",
            "price": 250.00,
            "createdAt": "2020-03-27T19:02:18.000+0000"
        }
    ]
}
```
---
### **Product Management**

1. List all products:

Method: GET
```url
http://localhost:8080/api/v1/products/
```

Sample Output:
```json
[
    {
        "id": 1,
        "name": "Mozarella Cheese",
        "description": "Italian Mozarella Cheese",
        "price": 250.00,
        "createdAt": "2020-03-26T12:53:00.000+0000",
        "categoryList": [
            {
                "id": 1,
                "name": "Milk and Dairy"
            }
        ]
    },
    {
        "id": 2,
        "name": "Beef Steak",
        "description": "Deicious Beef Steak",
        "price": 500.00,
        "createdAt": "2020-03-26T12:54:00.000+0000",
        "categoryList": [
            {
                "id": 2,
                "name": "Meats"
            }
        ]
    },
    {
        "id": 3,
        "name": "Chicken",
        "description": "Chicken Meat",
        "price": 400.00,
        "createdAt": "2020-03-26T12:55:00.000+0000",
        "categoryList": []
    }
]
```

2. Show product by ID:

Method: GET
```url
http://localhost:8080/api/v1/products/1
```

Sample Output:
```json
{
    "id": 1,
    "name": "Mozarella Cheese",
    "description": "Italian Mozarella Cheese",
    "price": 250.00,
    "createdAt": "2020-03-26T12:53:00.000+0000",
    "categoryList": [
        {
            "id": 1,
            "name": "Milk and Dairy"
        }
    ]
}
```

3. Create a new product:

Method: POST
```url
http://localhost:8080/api/v1/products
```
Sample input:
```json
{
    "name": "Mayonnaise",
    "description": "A thick cold condiment or dressing commonly used in sandwiches and composed salads or on French fries.",
    "price": 120,
    "categoryList": [1, 3]
}
```

Sample output:
```json
{
    "id": 35,
    "name": "Mayonnaise",
    "description": "A thick cold condiment or dressing commonly used in sandwiches and composed salads or on French fries.",
    "price": 120,
    "createdAt": null,
    "categoryList": [
        {
            "id": 1,
            "name": "Milk and Dairy"

        },
        {
            "id": 3,
            "name": "Grocery & Gourmet Food"
        }
    ]
}
```

4. Edit a product:

Method: POST
```url
http://localhost:8080/api/v1/products/35
```
Sample input:
```json
{
    "name": "Ketchup",
    "description": "Tomato ketchup is a sauce used as a condiment. Although original recipes used egg whites, mushrooms, oysters, grapes, mussels, or walnuts.",
    "price": 150,
    "categoryList": [3]
}
```

Sample output:
```json
{
    "id": 35,
    "name": "Ketchup",
    "description": "Tomato ketchup is a sauce used as a condiment. Although original recipes used egg whites, mushrooms, oysters, grapes, mussels, or walnuts.",
    "price": 150,
    "createdAt": "2020-03-28T03:29:35.000+0000",
    "categoryList": [
        {
            "id": 3,
            "name": "Grocery & Gourmet Food"
        }
    ]
}
```

---

### **Client Management**
1. List all clients:

Method: GET
```url
http://localhost:8080/api/v1/clients/
```

Sample Output:
```json
[
    {
        "id": 5,
        "name": "Hamza",
        "lastName": "Ghanam",
        "mobile": "0938688607",
        "saleList": [
            {
                "id": 40,
                "total": 2650.00,
                "createdAt": "2020-03-28T02:13:52.000+0000",
                "clientId": {
                    "id": 5,
                    "name": "Hamza",
                    "lastName": "Ghanam",
                    "mobile": "0938688607"
                },
                "sellerId": {
                    "id": 2,
                    "name": "Second Seller",
                    "mobile": "0988888888"
                },
                "soldProductList": [
                    {
                        "soldProductPK": {
                            "saleId": 40,
                            "productId": 1
                        },
                        "quantity": 3,
                        "salePrice": 250,
                        "product": {
                            "id": 1,
                            "name": "Mozarella Cheese",
                            "description": "Italian Mozarella Cheese",
                            "price": 250.00,
                            "createdAt": "2020-03-26T12:53:00.000+0000",
                            "categoryList": [
                                {
                                    "id": 1,
                                    "name": "Milk and Dairy"
                                }
                            ]
                        }
                    },
                    {
                        "soldProductPK": {
                            "saleId": 40,
                            "productId": 3
                        },
                        "quantity": 4,
                        "salePrice": 400,
                        "product": {
                            "id": 3,
                            "name": "Chicken",
                            "description": "Chicken Meat",
                            "price": 400.00,
                            "createdAt": "2020-03-26T12:55:00.000+0000",
                            "categoryList": []
                        }
                    },
                    {
                        "soldProductPK": {
                            "saleId": 40,
                            "productId": 31
                        },
                        "quantity": 1,
                        "salePrice": 300,
                        "product": {
                            "id": 31,
                            "name": "Chicken Rosto",
                            "description": "Pure chicken breast with pistachio stuffed in a net, naturally smoked with the finest types of wood.",
                            "price": 300.00,
                            "createdAt": "2020-03-27T19:28:51.000+0000",
                            "categoryList": [
                                {
                                    "id": 2,
                                    "name": "Meats"
                                },
                                {
                                    "id": 3,
                                    "name": "Grocery & Gourmet Food"
                                }
                            ]
                        }
                    }
                ]
            }
        ]
    }
]
```

2. Show client by ID:

Method: GET
```url
http://localhost:8080/api/v1/clients/5
```

Sample Output:
```json
{
    "id": 5,
    "name": "Hamza",
    "lastName": "Ghanam",
    "mobile": "0938688607",
    "saleList": [
        {
            "id": 40,
            "total": 2650.00,
            "createdAt": "2020-03-28T02:13:52.000+0000",
            "clientId": {
                "id": 5,
                "name": "Hamza",
                "lastName": "Ghanam",
                "mobile": "0938688607"
            },
            "sellerId": {
                "id": 2,
                "name": "Second Seller",
                "mobile": "0988888888"
            },
            "soldProductList": [
                {
                    "soldProductPK": {
                        "saleId": 40,
                        "productId": 1
                    },
                    "quantity": 3,
                    "salePrice": 250,
                    "product": {
                        "id": 1,
                        "name": "Mozarella Cheese",
                        "description": "Italian Mozarella Cheese",
                        "price": 250.00,
                        "createdAt": "2020-03-26T12:53:00.000+0000",
                        "categoryList": [
                            {
                                "id": 1,
                                "name": "Milk and Dairy"
                            }
                        ]
                    }
                },
                {
                    "soldProductPK": {
                        "saleId": 40,
                        "productId": 3
                    },
                    "quantity": 4,
                    "salePrice": 400,
                    "product": {
                        "id": 3,
                        "name": "Chicken",
                        "description": "Chicken Meat",
                        "price": 400.00,
                        "createdAt": "2020-03-26T12:55:00.000+0000",
                        "categoryList": []
                    }
                },
                {
                    "soldProductPK": {
                        "saleId": 40,
                        "productId": 31
                    },
                    "quantity": 1,
                    "salePrice": 300,
                    "product": {
                        "id": 31,
                        "name": "Chicken Rosto",
                        "description": "Pure chicken breast with pistachio stuffed in a net, naturally smoked with the finest types of wood.",
                        "price": 300.00,
                        "createdAt": "2020-03-27T19:28:51.000+0000",
                        "categoryList": [
                            {
                                "id": 2,
                                "name": "Meats"
                            },
                            {
                                "id": 3,
                                "name": "Grocery & Gourmet Food"
                            }
                        ]
                    }
                }
            ]
        }
    ]
}
```

3. Create a new client:

Method: POST
```url
http://localhost:8080/api/v1/clients
```
Sample input:
```json
{
	"name": "Steve",
	"lastName": "jobs",
	"mobile": "0963955555555"
}
```

Sample Output:
```json
{
    "id": 6,
    "name": "Steve",
    "lastName": "jobs",
    "mobile": "0963955555555",
    "saleList": null
}
```

4. Update a client:

Method: POST
```url
http://localhost:8080/api/v1/clients/6
```
Sample input:
```json
{
	"name": "Steve",
	"lastName": "jobs",
	"mobile": "0963 955 666 666"
}
```

Sample Output:
```json
{
    "id": 6,
    "name": "Steve",
    "lastName": "jobs",
    "mobile": "0963 955 666 666",
    "saleList": []
}
```

---

### **Seller Management**

1. List all sellers:

Method: GET
```url
http://localhost:8080/api/v1/sellers/
```

Sample Output:
```json
[
    {
        "id": 1,
        "name": "First Seller",
        "mobile": "0933333333",
        "saleList": [
            {
                "id": 29,
                "total": 2000.00,
                "createdAt": "2020-03-28T00:09:31.000+0000",
                "clientId": {
                    "id": 4,
                    "name": "Alice",
                    "lastName": "Smith",
                    "mobile": "00963938688607"
                },
                "sellerId": {
                    "id": 1,
                    "name": "First Seller",
                    "mobile": "0933333333"
                },
                "soldProductList": [
                    {
                        "soldProductPK": {
                            "saleId": 29,
                            "productId": 1
                        },
                        "quantity": 2,
                        "salePrice": 0,
                        "product": {
                            "id": 1,
                            "name": "Mozarella Cheese",
                            "description": "Italian Mozarella Cheese",
                            "price": 250.00,
                            "createdAt": "2020-03-26T12:53:00.000+0000",
                            "categoryList": [
                                {
                                    "id": 1,
                                    "name": "Milk and Dairy"
                                }
                            ]
                        }
                    },
                    {
                        "soldProductPK": {
                            "saleId": 29,
                            "productId": 2
                        },
                        "quantity": 3,
                        "salePrice": 0,
                        "product": {
                            "id": 2,
                            "name": "Beef Steak",
                            "description": "Deicious Beef Steak",
                            "price": 500.00,
                            "createdAt": "2020-03-26T12:54:00.000+0000",
                            "categoryList": [
                                {
                                    "id": 2,
                                    "name": "Meats"
                                }
                            ]
                        }
                    }
                ]
            },
            {
                "id": 30,
                "total": 2000.00,
                "createdAt": "2020-03-28T00:12:25.000+0000",
                "clientId": {
                    "id": 4,
                    "name": "Alice",
                    "lastName": "Smith",
                    "mobile": "00963938688607"
                },
                "sellerId": {
                    "id": 1,
                    "name": "First Seller",
                    "mobile": "0933333333"
                },
                "soldProductList": [
                    {
                        "soldProductPK": {
                            "saleId": 30,
                            "productId": 1
                        },
                        "quantity": 2,
                        "salePrice": 0,
                        "product": {
                            "id": 1,
                            "name": "Mozarella Cheese",
                            "description": "Italian Mozarella Cheese",
                            "price": 250.00,
                            "createdAt": "2020-03-26T12:53:00.000+0000",
                            "categoryList": [
                                {
                                    "id": 1,
                                    "name": "Milk and Dairy"
                                }
                            ]
                        }
                    },
                    {
                        "soldProductPK": {
                            "saleId": 30,
                            "productId": 2
                        },
                        "quantity": 3,
                        "salePrice": 0,
                        "product": {
                            "id": 2,
                            "name": "Beef Steak",
                            "description": "Deicious Beef Steak",
                            "price": 500.00,
                            "createdAt": "2020-03-26T12:54:00.000+0000",
                            "categoryList": [
                                {
                                    "id": 2,
                                    "name": "Meats"
                                }
                            ]
                        }
                    }
                ]
            }
        ]
    }
]
```
2. Show seller by ID:

Method: GET
```url
http://localhost:8080/api/v1/sellers/2
```

Sample Output:
```json
{
    "id": 2,
    "name": "Second Seller",
    "mobile": "0988888888",
    "saleList": [
        {
            "id": 40,
            "total": 2650.00,
            "createdAt": "2020-03-28T02:13:52.000+0000",
            "clientId": {
                "id": 5,
                "name": "Hamza",
                "lastName": "Ghanam",
                "mobile": "0938688607"
            },
            "sellerId": {
                "id": 2,
                "name": "Second Seller",
                "mobile": "0988888888"
            },
            "soldProductList": [
                {
                    "soldProductPK": {
                        "saleId": 40,
                        "productId": 1
                    },
                    "quantity": 3,
                    "salePrice": 250,
                    "product": {
                        "id": 1,
                        "name": "Mozarella Cheese",
                        "description": "Italian Mozarella Cheese",
                        "price": 250.00,
                        "createdAt": "2020-03-26T12:53:00.000+0000",
                        "categoryList": [
                            {
                                "id": 1,
                                "name": "Milk and Dairy"
                            }
                        ]
                    }
                },
                {
                    "soldProductPK": {
                        "saleId": 40,
                        "productId": 3
                    },
                    "quantity": 4,
                    "salePrice": 400,
                    "product": {
                        "id": 3,
                        "name": "Chicken",
                        "description": "Chicken Meat",
                        "price": 400.00,
                        "createdAt": "2020-03-26T12:55:00.000+0000",
                        "categoryList": []
                    }
                },
                {
                    "soldProductPK": {
                        "saleId": 40,
                        "productId": 31
                    },
                    "quantity": 1,
                    "salePrice": 300,
                    "product": {
                        "id": 31,
                        "name": "Chicken Rosto",
                        "description": "Pure chicken breast with pistachio stuffed in a net, naturally smoked with the finest types of wood.",
                        "price": 300.00,
                        "createdAt": "2020-03-27T19:28:51.000+0000",
                        "categoryList": [
                            {
                                "id": 2,
                                "name": "Meats"
                            },
                            {
                                "id": 3,
                                "name": "Grocery & Gourmet Food"
                            }
                        ]
                    }
                }
            ]
        }
    ]
}
```
3. Create a new seller:

Method: POST
```url
http://localhost:8080/api/v1/sellers
```
Sample input:
```json
{
	"name": "Bill Gates",
	"mobile": "0897977978"
}
```

Sample output:
```json
{
    "id": 4,
    "name": "Bill Gates",
    "mobile": "0897977978",
    "saleList": null
}
```

4. Edit a seller:

Method: POST
```url
http://localhost:8080/api/v1/sellers/4
```
Sample input:
```json
{
	"name": "Bill Smith",
	"mobile": "0897977978"
}
```

Sample output:
```json
{
    "id": 4,
    "name": "Bill Smith",
    "mobile": "0897977978",
    "saleList": []
}
```
---

### **Sales Management**

1. Fetch all sales operations:

Method: GET
```url
http://localhost:8080/api/v1/sales
```

Sample output:
```json
[
    {
        "id": 29,
        "total": 2000.00,
        "createdAt": "2020-03-28T00:09:31.000+0000",
        "clientId": {
            "id": 4,
            "name": "Alice",
            "lastName": "Smith",
            "mobile": "00963938688607"
        },
        "sellerId": {
            "id": 1,
            "name": "First Seller",
            "mobile": "0933333333"
        },
        "soldProductList": [
            {
                "soldProductPK": {
                    "saleId": 29,
                    "productId": 1
                },
                "quantity": 2,
                "salePrice": 0,
                "product": {
                    "id": 1,
                    "name": "Mozarella Cheese",
                    "description": "Italian Mozarella Cheese",
                    "price": 250.00,
                    "createdAt": "2020-03-26T12:53:00.000+0000",
                    "categoryList": [
                        {
                            "id": 1,
                            "name": "Milk and Dairy"
                        }
                    ]
                }
            },
            {
                "soldProductPK": {
                    "saleId": 29,
                    "productId": 2
                },
                "quantity": 3,
                "salePrice": 0,
                "product": {
                    "id": 2,
                    "name": "Beef Steak",
                    "description": "Deicious Beef Steak",
                    "price": 500.00,
                    "createdAt": "2020-03-26T12:54:00.000+0000",
                    "categoryList": [
                        {
                            "id": 2,
                            "name": "Meats"
                        }
                    ]
                }
            }
        ]
    }
]
```

2. Create new sale:

Method: POST
```url
http://localhost:8080/api/v1/sales/clientId/sellerId
```
Sample input:
clientId = 5, sellerId = 2
```json
[
	{
		"quantity": 3,
		"product": {"id": 1}
	},
	{
		"quantity": 5,
		"product": {"id": 3}
	},
	{
		"quantity": 1,
		"product": {"id": 31}
	}
]
```

Sample output:
```json
{
    "id": 41,
    "total": 3050,
    "createdAt": null,
    "clientId": {
        "id": 5,
        "name": "Hamza",
        "lastName": "Ghanam",
        "mobile": "0938688607"
    },
    "sellerId": {
        "id": 2,
        "name": "Second Seller",
        "mobile": "0988888888"
    },
    "soldProductList": [
        {
            "soldProductPK": {
                "saleId": 41,
                "productId": 1
            },
            "quantity": 3,
            "salePrice": 250.00,
            "product": {
                "id": 1,
                "name": "Mozarella Cheese",
                "description": "Italian Mozarella Cheese",
                "price": 250.00,
                "createdAt": "2020-03-26T12:53:00.000+0000",
                "categoryList": [
                    {
                        "id": 1,
                        "name": "Milk and Dairy"
                    }
                ]
            }
        },
        {
            "soldProductPK": {
                "saleId": 41,
                "productId": 3
            },
            "quantity": 5,
            "salePrice": 400.00,
            "product": {
                "id": 3,
                "name": "Chicken",
                "description": "Chicken Meat",
                "price": 400.00,
                "createdAt": "2020-03-26T12:55:00.000+0000",
                "categoryList": []
            }
        },
        {
            "soldProductPK": {
                "saleId": 41,
                "productId": 31
            },
            "quantity": 1,
            "salePrice": 300.00,
            "product": {
                "id": 31,
                "name": "Chicken Rosto",
                "description": "Pure chicken breast with pistachio stuffed in a net, naturally smoked with the finest types of wood.",
                "price": 300.00,
                "createdAt": "2020-03-27T19:28:51.000+0000",
                "categoryList": [
                    {
                        "id": 2,
                        "name": "Meats"
                    },
                    {
                        "id": 3,
                        "name": "Grocery & Gourmet Food"
                    }
                ]
            }
        }
    ]
}
```

3. Update quantities and prices of a sale:


Method: POST
```url
http://localhost:8080/api/v1/sales/updateqp
```
Sample input:
```json
{
	"quantity": 10,
	"salePrice": 300,
	"sale": {"id": 40},
	"product": {"id": 3}
}
```

Sample output:
```json
{
    "id": 41,
    "total": 4050.00,
    "createdAt": "2020-03-28T03:51:28.000+0000",
    "clientId": {
        "id": 5,
        "name": "Hamza",
        "lastName": "Ghanam",
        "mobile": "0938688607"
    },
    "sellerId": {
        "id": 2,
        "name": "Second Seller",
        "mobile": "0988888888"
    },
    "soldProductList": [
        {
            "soldProductPK": {
                "saleId": 41,
                "productId": 1
            },
            "quantity": 3,
            "salePrice": 250,
            "product": {
                "id": 1,
                "name": "Mozarella Cheese",
                "description": "Italian Mozarella Cheese",
                "price": 250.00,
                "createdAt": "2020-03-26T12:53:00.000+0000",
                "categoryList": [
                    {
                        "id": 1,
                        "name": "Milk and Dairy"
                    }
                ]
            }
        },
        {
            "soldProductPK": {
                "saleId": 41,
                "productId": 3
            },
            "quantity": 10,
            "salePrice": 300,
            "product": {
                "id": 3,
                "name": "Chicken",
                "description": "Chicken Meat",
                "price": 400.00,
                "createdAt": "2020-03-26T12:55:00.000+0000",
                "categoryList": []
            }
        },
        {
            "soldProductPK": {
                "saleId": 41,
                "productId": 31
            },
            "quantity": 1,
            "salePrice": 300,
            "product": {
                "id": 31,
                "name": "Chicken Rosto",
                "description": "Pure chicken breast with pistachio stuffed in a net, naturally smoked with the finest types of wood.",
                "price": 300.00,
                "createdAt": "2020-03-27T19:28:51.000+0000",
                "categoryList": [
                    {
                        "id": 2,
                        "name": "Meats"
                    },
                    {
                        "id": 3,
                        "name": "Grocery & Gourmet Food"
                    }
                ]
            }
        }
    ]
}
```

## License

The project is licensed under the [MIT license](https://opensource.org/licenses/MIT.

======
