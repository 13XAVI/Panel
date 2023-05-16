function calculateCosts() {
        // Get the number of panels from the input field
        var numPanels = parseInt(document.getElementById("num-panels").value);
          const averageCost = 78600;
          var cost = (numPanels * averageCost) / 3.5;

        // Update the cost element with the calculated value
        document.getElementById("cost").innerText = "FR " + cost.toFixed(2);
    }

function resetCalculator() {
  document.getElementById('num-panels').value = ''; // Reset the input field
  document.getElementById('results').innerHTML = ''; // Clear the result field
}

