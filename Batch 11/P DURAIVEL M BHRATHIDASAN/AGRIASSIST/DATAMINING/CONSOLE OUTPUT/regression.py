import pandas as pd
from pandas import DataFrame
import conversion as conv
from sklearn import linear_model
from sklearn.preprocessing import LabelEncoder
import statsmodels.api as sm
agri = pd.read_csv('ye.csv')
dfa = pd.DataFrame(agri,columns=['District','Season','Crop','AreaN','ProductionN'])
print(dfa)
print("\n")
inputs = agri.drop(['districtdummy','State_Name','Crop_Year'],axis='columns')
le_district = LabelEncoder()
le_season = LabelEncoder()
le_crop = LabelEncoder()
inputs['District'] = le_district.fit_transform(inputs['District'])
inputs['Season'] = le_season.fit_transform(inputs['Season'])
inputs['Crop'] = le_crop.fit_transform(inputs['Crop'])
print(inputs)
X = inputs[['AreaN','District','Season','Crop']] # here we have 2 variables for multiple regression. If you just want to use one variable for simple linear regression, then use X = dfaa['Interest_Rate'] for example.Alternatively, you may add additional variables within the brackets
Y = inputs['ProductionN']

# with sklearn
regr = linear_model.LinearRegression()
regr.fit(X, Y)

print('Intercept: \n', regr.intercept_)
print('Coefficients: \n', regr.coef_)
# prediction with sklearn
Area = 10
District=conv.disStringToNum("ERODE")
Season=conv.seasStringToNum("Kharif")
Crop=conv.cropStringToNum("Rice")
print ('Predicted Rice Cultivation: \n', regr.predict([[Area,District,Season,Crop]]))