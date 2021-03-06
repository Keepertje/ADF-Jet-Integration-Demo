define(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout','ojs/ojbutton', 'ojs/ojchart', 'ojs/ojtoolbar'], function (oj, ko, $) {

    function AmisChartComponentModel(context) {
        var self = this;
    
        context.props.then(function (propertymap) {
            self.properties = propertymap;
            var dataAsJson = JSON.parse(propertymap.chartData);
            var barData = self.createBarSeries(dataAsJson.values);
            /* Chart Data */
            self.barSeriesValue = ko.observableArray(barData);
        })
        

        self.createBarSeries = function (jsonDataArray) {
            var data = [];
            jsonDataArray.forEach(function (item, index, arr) {
                data.push( {
                    "name" : item.FirstName, "items" : [item.Salary]
                });
            })
            return data;
        }
        
         var barGroups = ["Employees"];
        self.barGroupsValue = ko.observableArray(barGroups);
        self.stackValue = ko.observable('off');
        self.orientationValue = ko.observable('vertical');
        
        /* toggle buttons*/
        self.stackOptions = [
            {id: 'unstacked', label: 'unstacked', value: 'off'},
            {id: 'stacked', label: 'stacked', value: 'on'}
        ];
        self.orientationOptions = [
            {id: 'vertical', label: 'vertical', value: 'vertical' },
            {id: 'horizontal', label: 'horizontal', value: 'horizontal'}
        ];
        
    }
    return AmisChartComponentModel;

});