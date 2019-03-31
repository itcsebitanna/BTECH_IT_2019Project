from django.shortcuts import render

from django.http import HttpResponse
import pandas as pd
from sklearn.preprocessing import LabelEncoder
from sklearn import tree

def index(request):
    districtQuery=request.GET['dist']
    cropQuery=request.GET['crop']
    seasonQuery=request.GET['season']
    periodQuery=request.GET['period']
    district =int("{}".format(districtQuery))
    crop=int("{}".format(cropQuery))
    season=int("{}".format(seasonQuery))
    period=int("{}".format(periodQuery))
    df = pd.read_csv(r'''D:\AGRI FINAL YEAR\UPDATED MINING\crowdsource1.csv''')
    df.head()
    inputs = df.drop(['dummy','disease'],axis='columns')
    target = df['dummy']
    le_district = LabelEncoder()
    le_crop = LabelEncoder()
    le_season = LabelEncoder()
    le_periods = LabelEncoder()
    inputs['district'] = le_district.fit_transform(inputs['district'])
    inputs['crop'] = le_crop.fit_transform(inputs['crop'])
    inputs['season'] = le_season.fit_transform(inputs['season'])
    inputs['periods'] = le_periods.fit_transform(inputs['periods'])
    inputs_n = inputs.drop([],axis='columns')
    print(inputs_n)
    target
    model = tree.DecisionTreeClassifier()
    model.fit(inputs_n, target)
    model.score(inputs_n,target)
    print(model.predict([[district,crop,season,period]]))
    print(model.predict([[district,crop,season,period]]))
    print(district)
    print(crop)
    print(season)
    print(period)
    return HttpResponse(model.predict([[district,crop,season,period]]))
