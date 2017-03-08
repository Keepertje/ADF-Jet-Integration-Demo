define([ 'ojs/ojcore', 'knockout', 'jquery' ], function(oj, ko, $) {
  'use strict';
  function AmisPersonComponentModel(context) {
    var self = this;

    context.props.then(function(propertymap){
        self.properties = propertymap;
        var person = propertymap.amisPersonName;
        self.upperCaseName = person.toUpperCase();
    })
  }

  //Return a constructor for this componentModel
  return AmisPersonComponentModel;
});
