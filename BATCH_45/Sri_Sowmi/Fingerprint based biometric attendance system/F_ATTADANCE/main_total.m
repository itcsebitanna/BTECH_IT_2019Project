clear all; clc; addpath(genpath(pwd));

load('db.mat');


filename='101_4.tif';
img = imread(filename);
if ndims(img) == 3; img = rgb2gray(img); end  % Color Images
disp(['Extracting features from ' filename ' ...']);
ffnew=ext_finger(img,1);

S=zeros(10,1);
for i=1:10
    second=['10' num2str(fix((i-1)/8)+1) '_' num2str(mod(i-1,8)+1)];
   fprintf(['Computing similarity between ' filename ' and ' second ' from FVC2002 : ']);
    S(i)=match(ffnew,ff{i});
    fprintf([num2str(S(i)) '\n']);
    drawnow
    
    

        
end
%% OFFER MATCHED FINGERPRINTS
Matched_FigerPrints=find(S==1)
    if (Matched_FigerPrints == 1)      
            fprintf(['Raja ECE ROLL.NO:1 Present']);      
    end
   if (Matched_FigerPrints == 2)      
            fprintf(['Rani ECE ROLL.NO:2 Present']);      
        end
   if (Matched_FigerPrints == 3)      
            fprintf(['Karan ECE ROLL.NO:3 Present']);      
   end
     if (Matched_FigerPrints == 4)      
            fprintf(['jhon ECE ROLL.NO:4 Present']);      
     end
       if (Matched_FigerPrints == 5)      
            fprintf(['jhon1 ECE ROLL.NO:5 Present']);      
  end

 if (Matched_FigerPrints == 6)      
            fprintf(['prabhu ECE ROLL.NO:6 Present']);      
    end
   if (Matched_FigerPrints == 7)      
            fprintf(['venkat ECE ROLL.NO:7 Present']);      
        end
   if (Matched_FigerPrints == 8)      
            fprintf(['raju ECE ROLL.NO:8 Present']);      
   end
     if (Matched_FigerPrints == 9)      
            fprintf(['suba ECE ROLL.NO:9 Present']);      
     end
       if (Matched_FigerPrints == 10)      
            fprintf(['viki ECE ROLL.NO:10 Present']);      
  end

