import pandas as pd
from sklearn.preprocessing import LabelEncoder
from sklearn import tree
df = pd.read_csv("crowdsource1.csv")
df.head()
inputs = df.drop(['dummy','disease'],axis='columns')
print(inputs)
target = df['dummy']
le_district = LabelEncoder()
le_crop = LabelEncoder()
le_season = LabelEncoder()
le_periods = LabelEncoder()
inputs['district'] = le_district.fit_transform(inputs['district'])
inputs['crop'] = le_crop.fit_transform(inputs['crop'])
inputs['season'] = le_season.fit_transform(inputs['season'])
inputs['periods'] = le_periods.fit_transform(inputs['periods'])
inputs
inputs_n = inputs.drop([],axis='columns')
print(inputs_n)
target
model = tree.DecisionTreeClassifier()
model.fit(inputs_n, target)
model.score(inputs_n,target)
print(model.predict([[2,0,3,4]]))
print(model.predict([[0,1,0,1]]))
