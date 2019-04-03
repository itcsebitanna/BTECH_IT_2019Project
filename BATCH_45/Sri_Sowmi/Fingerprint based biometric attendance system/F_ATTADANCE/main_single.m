clear all; clc; addpath(genpath(pwd));

%% EXTRACT FEATURES FROM AN ARBITRARY FINGERPRINT
filename='101_1.tif';
img = imread(filename);
if ndims(img) == 3; img = rgb2gray(img); end  % Color Images
disp(['Extracting features from ' filename ' ...']);
ffnew=ext_finger(img,1);

%% GET FEATURES OF AN ARBITRARY FINGERPRINT FROM THE TEMPLATE AND MATCH IT WITH FIRST ONE
load('db.mat'); i=2;
second=['10' num2str(fix((i-1)/8)+1) '_' num2str(mod(i-1,8)+1)];
disp(['Computing similarity between ' filename ' and ' second ' from FVC2002']);
S=match(ffnew,ff{i},1);
