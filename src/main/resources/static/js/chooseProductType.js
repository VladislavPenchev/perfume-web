function choseProductType() {
    const chooseProductTypeForm = document.getElementsByClassName('choose-product-type')[0];
    const chooseProductTypeSelect = document.getElementById('productTypeSection');

    chooseProductTypeSelect.addEventListener('change', showHideForm);

    function showHideForm() {
        Array.from(document.getElementsByClassName('select-form'))
            .forEach(el => {
                if (!el.classList.contains('hidden')) {
                    el.classList.add('hidden')
                }
            })

        let optionInput = chooseProductTypeSelect.options[chooseProductTypeSelect.selectedIndex].text.toLowerCase();

        const perfumeForm = document.getElementsByClassName(optionInput)[0];
        perfumeForm.classList.remove('hidden')
        chooseProductTypeForm.classList.add('show');
    }
}

choseProductType();