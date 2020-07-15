function allProductTypes() {
    const selectProductTypes = document.getElementById('productTypeSection');

    fetch('http://localhost:8080/api/product-types')
        .then(response => response.json())
        .then(data => data.forEach(element => {
            let option = document.createElement('option');
            option.text = element;
            selectProductTypes.add(option);
        }));
}

allProductTypes();