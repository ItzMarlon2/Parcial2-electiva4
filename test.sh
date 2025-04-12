#!/bin/bash
# Script para probar endpoints según las colecciones Postman "parcial" y "taller"

# Función para imprimir separadores y mensajes
print_separator() {
  echo "==========================================="
}

print_separator
echo "Iniciando pruebas de endpoints de la colección 'parcial'"
print_separator

# Grupo: productos
echo "-> Crear Producto"
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d "{\"name\":\"product1\",\"description\":\"description\",\"price\":2}"
echo -e "\n"

echo "-> Actualizar Producto"
curl -X PUT http://localhost:8080/api/products/2 \
  -H "Content-Type: application/json" \
  -d "{\"name\":\"product2\",\"description\":\"description\",\"price\":10}"
echo -e "\n"

echo "-> Eliminar Producto"
curl -X DELETE http://localhost:8080/api/products/1
echo -e "\n"

echo "-> Consultar Producto por ID"
curl http://localhost:8080/api/products/2
echo -e "\n"

echo "-> Consultar Todos los Productos"
curl http://localhost:8080/api/products
echo -e "\n"

print_separator
echo "Iniciando pruebas de endpoints de la colección 'taller'"
print_separator

echo "-> New Request (GET)"
curl http://localhost:8081/
echo -e "\n"

print_separator
echo "Pruebas finalizadas."
print_separator

